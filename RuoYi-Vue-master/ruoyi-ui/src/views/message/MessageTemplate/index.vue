<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="模版名称" prop="messagetemplatename">
        <el-input
          v-model="queryParams.messagetemplatename"
          placeholder="请输入模版名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="模版拥有者" prop="templateownerid">
        <el-input
          v-model="queryParams.templateownerid"
          placeholder="请输入模版拥有者"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['message:MessageTemplate:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['message:MessageTemplate:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['message:MessageTemplate:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['message:MessageTemplate:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="MessageTemplateList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="id" />
      <el-table-column label="模版名称" align="center" prop="messagetemplatename" />
      <el-table-column label="模版拥有者" align="center" prop="templateownerid" />
      <el-table-column label="消息消费者" align="center" prop="consumerid" />
      <el-table-column label="消息内容" align="center" prop="messagecontent" />
      <el-table-column label="消费者类型" align="center" prop="consumerjudge">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_template_type" :value="scope.row.consumerjudge"/>
        </template>
      </el-table-column>
      <el-table-column label="消息链接" align="center" prop="messagelink" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['message:MessageTemplate:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['message:MessageTemplate:remove']"
          >删除</el-button>
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

    <!-- 添加或修改消息模版对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="模版名称" prop="messagetemplatename">
          <el-input v-model="form.messagetemplatename" placeholder="请输入模版名称" />
        </el-form-item>
        <el-form-item label="模版拥有者" prop="templateownerid">
          <el-input v-model="form.templateownerid" placeholder="请输入模版拥有者" />
        </el-form-item>
        <el-form-item label="消息消费者" prop="consumerid">
          <el-input v-model="form.consumerid" placeholder="请输入消息消费者" />
        </el-form-item>
        <div>
        <el-form-item label="模版" prop="readjudge">
          <el-select v-model="form.part" placeholder="请选择模版组件" clearable>
            <el-option
              v-for="dict in dict.type.sys_templatepart_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
          <el-button type="primary" @click="insertContentHandle" id="insertpart" :style="{marginLeft:'10%'}">插入</el-button>
        </el-form-item>
        <el-form-item label="消息内容">
<!--          <editor v-model="form.messagecontent" :min-height="192"/>-->
          <el-input v-model="form.messagecontent" type="textarea" @blur="blurEvent" placeholder="请输入消息内容"
                    :autosize="{minRows: 4, maxRows: 4}" :style="{width: '100%'}  "></el-input>
        </el-form-item>
        </div>
        <el-form-item label="消费者类型" prop="consumerjudge">
          <el-select v-model="form.consumerjudge" placeholder="请选择消费者类型">
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
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listMessageTemplate, getMessageTemplate, delMessageTemplate, addMessageTemplate, updateMessageTemplate } from "@/api/message/MessageTemplate";

export default {
  name: "MessageTemplate",
  dicts: ['sys_template_type','sys_templatepart_type'],

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
      // 消息模版表格数据
      MessageTemplateList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        messagetemplatename: null,
        templateownerid: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        messagetemplatename: [
          { required: true, message: "模版名称不能为空", trigger: "blur" }
        ],
        templateownerid: [
          { required: true, message: "模版拥有者不能为空", trigger: "blur" }
        ],
      },
      blurIndex: null,//记录光标位置

    };
  },
  created() {
    this.getList();
  },
  methods: {


    blurEvent(e) {
      this.blurIndex = e.srcElement.selectionStart;
      console.log(e)
      console.log(e.srcElement)
      console.log(e.srcElement.selectionStart) //光标所在的位置
    },
    // 插入内容的方法
    insertContentHandle() {
      let index=this.blurIndex
      let str=this.form.messagecontent
      this.form.messagecontent=str.slice(0, index) + this.form.part + str.slice(index);
    },

    /** 查询消息模版列表 */
    getList() {
      this.loading = true;
      listMessageTemplate(this.queryParams).then(response => {
        this.MessageTemplateList = response.rows;
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
        messagetemplatename: null,
        templateownerid: null,
        consumerid: null,
        messagecontent: null,
        consumerjudge: null,
        messagelink: null,
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
      this.title = "添加消息模版";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getMessageTemplate(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改消息模版";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateMessageTemplate(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addMessageTemplate(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除消息模版编号为"' + ids + '"的数据项？').then(function() {
        return delMessageTemplate(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('message/MessageTemplate/export', {
        ...this.queryParams
      }, `MessageTemplate_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
