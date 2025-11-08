<template>
  <!-- 应用程序容器 -->
  <div class="app-container">
    <!-- 搜索表单卡片，当showSearch为true时显示 -->
    <el-card :class="['search-card', 'shadow-card']" v-show="showSearch">
      <!-- 查询参数表单 -->
      <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" label-width="80px">
        <!-- 使用el-row和el-col布局搜索字段 -->
        <el-row :gutter="20">
          <el-col :span="6">
            <!-- 一级分类查询输入框 -->
            <el-form-item label="一级分类" prop="level1">
              <el-input v-model="queryParams.level1" placeholder="请输入一级分类" clearable @keyup.enter.native="handleQuery" />
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <!-- 二级分类查询输入框 -->
            <el-form-item label="二级分类" prop="level2">
              <el-input v-model="queryParams.level2" placeholder="为空时应填入“未分类”" clearable @keyup.enter.native="handleQuery" />
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <!-- 三级分类查询输入框 -->
            <el-form-item label="三级分类" prop="level3">
              <el-input v-model="queryParams.level3" placeholder="为空时应填入“未分类”" clearable @keyup.enter.native="handleQuery" />
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <!-- 药品名称查询输入框 -->
            <el-form-item label="药品名称" prop="drugName">
              <el-input v-model="queryParams.drugName" placeholder="请输入药品名称" clearable @keyup.enter.native="handleQuery" />
            </el-form-item>
          </el-col>
        </el-row>
        <!-- 搜索按钮行 -->
        <el-row type="flex" justify="end">
          <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        </el-row>
      </el-form>
    </el-card>

    <!-- 操作按钮卡片 -->
    <el-card :class="['action-buttons-card', 'shadow-card']">
      <!-- 操作按钮行 -->
      <el-row :gutter="1" type="flex" justify="start">
        <!-- 新增按钮 -->
        <el-col :span="4">
          <el-button
            type="primary"
            plain
            icon="el-icon-plus"
            size="small"
            @click="handleAdd"
            v-hasPermi="['system:chstcmcatalog:add']"
          >新增</el-button>
        </el-col>
        <!-- 修改按钮 -->
        <el-col :span="4">
          <el-button
            type="success"
            plain
            icon="el-icon-edit"
            size="small"
            :disabled="single"
            @click="handleUpdate"
            v-hasPermi="['system:chstcmcatalog:edit']"
          >修改</el-button>
        </el-col>
        <!-- 删除按钮 -->
        <el-col :span="4">
          <el-button
            type="danger"
            plain
            icon="el-icon-delete"
            size="small"
            :disabled="multiple"
            @click="handleDelete"
            v-hasPermi="['system:chstcmcatalog:remove']"
          >删除</el-button>
        </el-col>
        <!-- 导出按钮 -->
        <el-col :span="4">
          <el-button
            type="warning"
            plain
            icon="el-icon-download"
            size="small"
            @click="handleExport"
            v-hasPermi="['system:chstcmcatalog:export']"
          >导出</el-button>
        </el-col>
        <!-- 显示/隐藏搜索栏工具 -->
        <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
      </el-row>

    </el-card>

    <!-- 数据表格卡片 -->
    <el-card :class="['data-table-card', 'shadow-card']">
      <!-- 表格组件，加载状态由loading控制 -->
      <el-table v-loading="loading" :data="chstcmcatalogList" @selection-change="handleSelectionChange" stripe>
        <!-- 多选列 -->
        <el-table-column type="selection" width="55" align="center" />
        <!-- ID列 -->
        <el-table-column label="ID" align="center" prop="id" width="70" />
        <!-- 一级分类列 -->
        <el-table-column label="一级分类" align="center" prop="level1" />
        <!-- 二级分类列 -->
        <el-table-column label="二级分类" align="center" prop="level2" />
        <!-- 三级分类列 -->
        <el-table-column label="三级分类" align="center" prop="level3" />
        <!-- 药品类型列 -->
        <el-table-column label="药品类型" align="center" prop="type" />
        <!-- 药品名称列 -->
        <el-table-column label="药品名称" align="center" prop="drugName" />
        <!-- 使用限制列 -->
        <el-table-column label="使用限制" align="center" prop="constrain" />
        <!-- 操作列 -->
        <el-table-column label="操作" align="center" width="180">
          <template slot-scope="scope">
            <!-- 修改按钮 -->
            <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row)"
              v-hasPermi="['system:chstcmcatalog:edit']"
            >修改</el-button>
            <!-- 删除按钮 -->
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
              v-hasPermi="['system:chstcmcatalog:remove']"
            >删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <!-- 分页组件 -->
      <pagination
        v-show="total > 0"
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="getList"
      />
    </el-card>

    <!-- 添加或编辑对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <!-- 对话框表单 -->
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <!-- 一级分类输入框 -->
        <el-form-item label="一级分类" prop="level1">
          <el-input v-model="form.level1" placeholder="请输入一级分类" />
        </el-form-item>
        <!-- 二级分类输入框 -->
        <el-form-item label="二级分类" prop="level2">
          <el-input v-model="form.level2" placeholder="请输入二级分类，为空时应填入“未分类”" />
        </el-form-item>
        <!-- 三级分类输入框 -->
        <el-form-item label="三级分类" prop="level3">
          <el-input v-model="form.level3" placeholder="请输入三级分类，为空时应填入“未分类”" />
        </el-form-item>
        <!-- 药品名称输入框 -->
        <el-form-item label="药品名称" prop="drugName">
          <el-input v-model="form.drugName" placeholder="请输入药品名称" />
        </el-form-item>
        <!-- 使用限制文本区域 -->
        <el-form-item label="使用限制" prop="constrain">
          <el-input v-model="form.constrain" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <!-- 对话框底部按钮 -->
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
// 引入API方法
import { listChstcmcatalog, getChstcmcatalog, delChstcmcatalog, addChstcmcatalog, updateChstcmcatalog } from "@/api/system/chstcmcatalog";

export default {
  name: "Chstcmcatalog",
  data() {
    return {
      // 加载状态
      loading: true,
      // 当前选择的ID列表
      ids: [],
      // 单个条目是否被选中
      single: true,
      // 是否有多个条目被选中
      multiple: true,
      // 是否显示搜索表单
      showSearch: true,
      // 总记录数
      total: 0,
      // 药品信息列表
      chstcmcatalogList: [],
      // 对话框标题
      title: "",
      // 控制对话框显示
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1, // 当前页码
        pageSize: 10, // 每页大小
        level1: null, // 一级分类
        level2: null, // 二级分类
        level3: null, // 三级分类
        type: null, // 药品类型
        drugName: null, // 药品名称
        constrain: null // 使用限制
      },
      // 对话框表单数据
      form: {},
      // 表单验证规则
      rules: {
        level1: [
          { required: true, message: "一级分类不能为空", trigger: "blur" }
        ],
        level2: [
          { required: true, message: "二级分类，不能为空", trigger: "blur" }
        ],
        level3: [
          { required: true, message: "三级分类，不能为空", trigger: "blur" }
        ],
        type: [
          { required: true, message: "药品类型不能为空", trigger: "change" }
        ],
        drugName: [
          { required: true, message: "药品名称不能为空", trigger: "blur" }
        ],
      }
    };
  },
  // 组件创建时调用的方法
  created() {
    this.getList();
  },
  methods: {
    // 获取药品信息列表
    getList() {
      this.loading = true;
      listChstcmcatalog(this.queryParams).then(response => {
        this.chstcmcatalogList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消对话框并重置表单
    cancel() {
      this.open = false;
      this.reset();
    },
    // 重置对话框表单
    reset() {
      this.form = {
        id: null,
        level1: null,
        level2: null,
        level3: null,
        type: null,
        drugName: null,
        constrain: null
      };
      this.resetForm("form");
    },
    // 触发查询
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    // 重置查询条件
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 表格选择变化事件
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    // 打开添加对话框
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加药品信息";
    },
    // 打开编辑对话框
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getChstcmcatalog(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改药品信息";
      });
    },
    // 提交表单
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateChstcmcatalog(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addChstcmcatalog(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    // 删除药品信息
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除药品信息编号为"' + ids + '"的数据项？').then(() => {
        return delChstcmcatalog(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    // 导出药品信息
    handleExport() {
      this.download('system/chstcmcatalog/export', {
        ...this.queryParams
      }, `chstcmcatalog_${new Date().getTime()}.xlsx`);
    }
  }
};
</script>

<style scoped>
/* 应用程序容器样式 */
.app-container {
  padding: 10px;
  background-color: #f5f7fa;
}

/* 
  该样式用于搜索卡、操作按钮卡和数据表格卡的样式定义 
*/
.search-card, .action-buttons-card, .data-table-card {
  margin-bottom: 10px; /* 定义底部外边距 */
  border-radius: 8px; /* 设置圆角 */
  transition: all .3s; /* 设置过渡效果 */
  background-color: #fff; /* 设置背景色为白色 */
  padding: 20px; /* 定义内边距 */
}

/* 阴影样式 */
.shadow-card {
  box-shadow: 0 2px 12px rgba(0,0,0,.1);
}

/* 鼠标悬停阴影加深 */
.shadow-card:hover {
  box-shadow: 0 8px 20px rgba(0,0,0,.15);
}

/* 表格边框圆角 */
.el-table {
  border-radius: 8px;
}

/* 对话框底部按钮样式 */
.dialog-footer {
  display: flex;
  justify-content: flex-end;
}
</style>