<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="消息生产者id" prop="producerid">
        <el-input
          v-model="queryParams.producerid"
          placeholder="请输入消息生产者id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="消息消费者id" prop="consumerid">
        <el-input
          v-model="queryParams.consumerid"
          placeholder="请输入消息消费者id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="发送时间" prop="producerdate">
        <el-date-picker clearable
          v-model="queryParams.producerdate"
          type="date"
          value-format="yyyy-MM-dd HH:mm:ss"
          placeholder="请选择发送时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="是否已读" prop="readjudge">
        <el-select v-model="queryParams.readjudge" placeholder="请选择是否已读" clearable>
          <el-option
            v-for="dict in dict.type.sys_read_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">

      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['message:ConsumerMessage:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="ConsumerMessageList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="id" />
      <el-table-column label="消息生产者id" align="center" prop="producerid" />
      <el-table-column label="消息消费者id" align="center" prop="consumerid" />
      <el-table-column label="消息内容" align="center" prop="messagecontent" />
      <el-table-column label="发送时间" align="center" prop="producerdate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.producerdate, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="是否已读" align="center" prop="readjudge">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_read_type" :value="scope.row.readjudge"/>
        </template>
      </el-table-column>
      <el-table-column label="阅读时间" align="center" prop="readtime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.readtime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="消息链接" align="center" prop="messagelink" />

    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />


  </div>
</template>

<script>
import { listConsumerMessage, getConsumerMessage, delConsumerMessage, addConsumerMessage, updateConsumerMessage } from "@/api/message/ConsumerMessage";

export default {
  name: "ConsumerMessage",
  dicts: ['sys_read_type'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 接收方消息表格数据
      ConsumerMessageList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        producerid: null,
        consumerid: null,
        producerdate: null,
        readjudge: null,
        readtime: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        producerid: [
          { required: true, message: "消息生产者id不能为空", trigger: "blur" }
        ],
        consumerid: [
          { required: true, message: "消息消费者id不能为空", trigger: "blur" }
        ],
        producerdate: [
          { required: true, message: "发送时间不能为空", trigger: "blur" }
        ],
        readjudge: [
          { required: true, message: "是否已读不能为空", trigger: "change" }
        ],
        delFlag: [
          { required: true, message: "删除标志不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询接收方消息列表 */
    getList() {
      this.loading = true;
      listConsumerMessage(this.queryParams).then(response => {
        this.ConsumerMessageList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        producerid: null,
        consumerid: null,
        messagecontent: null,
        producerdate: null,
        readjudge: null,
        messagelink: null,
        delFlag: null,
        readtime: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加接收方消息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getConsumerMessage(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改接收方消息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateConsumerMessage(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addConsumerMessage(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除接收方消息编号为"' + ids + '"的数据项？').then(function() {
        return delConsumerMessage(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('message/ConsumerMessage/export', {
        ...this.queryParams
      }, `ConsumerMessage_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
