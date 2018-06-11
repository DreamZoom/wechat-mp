<template>
  <el-container class="main-container">
    <el-aside class="left-aside">
      <div class="left-header">管理后台</div>
      <el-menu default-active="2" class="left-menu" @select="handleSelect" @open="handleOpen" @close="handleClose" background-color="#222C3C" text-color="#fff" active-text-color="#ffd04b">
        <el-menu-item index="1">
          <i class="el-icon-menu"></i>
          <span slot="title">主体信息</span>
        </el-menu-item>
        <el-menu-item index="2">
          <i class="el-icon-menu"></i>
          <span slot="title">消息回复</span>
        </el-menu-item>
        <el-menu-item index="3">
          <i class="el-icon-document"></i>
          <span slot="title">菜单管理</span>
        </el-menu-item>
        <el-menu-item index="4">
          <i class="el-icon-setting"></i>
          <span slot="title">用户管理</span>
        </el-menu-item>
  
        <el-menu-item index="5">
          <i class="el-icon-setting"></i>
          <span slot="title">主体设置</span>
        </el-menu-item>
  
      </el-menu>
    </el-aside>
    <el-container>
      <el-header class="main-header">
        <el-dropdown>
          <i class="el-icon-setting" style="margin-right: 15px"></i>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>查看</el-dropdown-item>
            <el-dropdown-item>新增</el-dropdown-item>
            <el-dropdown-item>删除</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
        <span>王小虎</span>
      </el-header>
      <el-main class="main-view">
        <router-view/>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  name: "App",
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    handleSelect(key, keyPath) {
      console.log(key);
    }
  }
};
</script>

<style>
html,
body {
  padding: 0px;
  margin: 0px;
  height: 100%;
  width: 100%;
  position: relative;
}

.main-container {
  height: 100%;
  width: 100%;
}

.left-aside {
  width: 240px !important;
  background: #222c3c;
}

.left-menu {
  border-right: solid 1px transparent;
}

.main-header {
  background: #ffffff;
}

.left-header {
  color: #ccc;
  text-align: center;
  border-bottom: solid 1px #2a3547;
  height: 60px;
  line-height: 60px;
}

.main-view{
  background: #eff3f6;
}
</style>
