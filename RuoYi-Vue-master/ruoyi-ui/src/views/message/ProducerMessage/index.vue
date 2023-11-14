<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="发送方id" prop="producerid">
        <el-input
          v-model="queryParams.producerid"
          placeholder="请输入发送方id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="接收方id" prop="consumerid">
        <el-input
          v-model="queryParams.consumerid"
          placeholder="请输入接收方id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="接收方类型" prop="consumerjudge">
        <el-select v-model="queryParams.consumerjudge" placeholder="请选择接收方类型" clearable>
          <el-option
            v-for="dict in dict.type.sys_template_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="发送时间" prop="producerdate">
        <el-date-picker clearable
          v-model="queryParams.producerdate"
          type="date"
          value-format="yyyy-MM-dd HH:mm:ss"
          placeholder="请选择发送时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="是否已发送" prop="send">
        <el-select v-model="queryParams.send" placeholder="请选择是否已发送" clearable>
          <el-option
            v-for="dict in dict.type.sys_send_type"
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
          v-hasPermi="['message:ProducerMessage:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="ProducerMessageList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="id" />
      <el-table-column label="发送方id" align="center" prop="producerid" />
      <el-table-column label="接收方id" align="center" prop="consumerid" />
      <el-table-column label="消息内容" align="center" prop="messagecontent" />
      <el-table-column label="接收方类型" align="center" prop="consumerjudge">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_template_type" :value="scope.row.consumerjudge"/>
        </template>
      </el-table-column>
      <el-table-column label="发送时间" align="center" prop="producerdate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.producerdate, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="消息链接" align="center" prop="messagelink" />
      <el-table-column label="是否已发送" align="center" prop="send">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_send_type" :value="scope.row.send"/>
        </template>
      </el-table-column>
      <el-table-column label="预定发送时间" align="center" prop="senddate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.senddate, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>

    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改发送方消息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="发送方id" prop="producerid">
          <el-input v-model="form.producerid" placeholder="请输入发送方id" />
        </el-form-item>
        <el-form-item label="接收方id" prop="consumerid">
          <el-input v-model="form.consumerid" placeholder="请输入接收方id" />
        </el-form-item>
        <el-form-item label="消息内容">
          <editor v-model="form.messagecontent" :min-height="192"/>
        </el-form-item>
        <el-form-item label="接收方类型" prop="consumerjudge">
          <el-select v-model="form.consumerjudge" placeholder="请选择接收方类型">
            <el-option
              v-for="dict in dict.type.sys_template_type"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="消息链接" prop="messagelink">
          <el-input v-model="form.messagelink" placeholder="请输入消息链接" />
        </el-form-item>
        <el-form-item label="预定发送时间" prop="senddate">
          <el-date-picker clearable
            v-model="form.senddate"
            type="date"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="请选择预定发送时间">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listProducerMessage, getProducerMessage, delProducerMessage, addProducerMessage, updateProducerMessage } from "@/api/message/ProducerMessage";

export default {
  name: "ProducerMessage",
  dicts: ['sys_send_type', 'sys_template_type'],
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
      // 发送方消息表格数据
      ProducerMessageList: [],
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
        consumerjudge: null,
        producerdate: null,
        send: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        producerid: [
          { required: true, message: "发送方id不能为空", trigger: "blur" }
        ],
        consumerid: [
          { required: true, message: "接收方id不能为空", trigger: "blur" }
        ],
        consumerjudge: [
          { required: true, message: "接收方类型不能为空", trigger: "change" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询发送方消息列表 */
    getList() {
      this.loading = true;
      listProducerMessage(this.queryParams).then(response => {
        this.ProducerMessageList = response.rows;
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
        consumerjudge: null,
        producerdate: null,
        messagelink: null,
        send: null,
        senddate: null,
        delFlag: null
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
      this.title = "添加发送方消息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getProducerMessage(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改发送方消息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateProducerMessage(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addProducerMessage(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除发送方消息编号为"' + ids + '"的数据项？').then(function() {
        return delProducerMessage(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('message/ProducerMessage/export', {
        ...this.queryParams
      }, `ProducerMessage_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
