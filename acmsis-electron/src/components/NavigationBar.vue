<template>
  <el-menu
      :default-active="$route.path"
      class="el-menu-vertical-demo"
      :collapse="isCollapse"
      :router="true"
      @mouseover="isCollapse=false"
      @mouseleave="isCollapse=true"
  >
    <el-menu-item index="/home">
      <el-icon><location /></el-icon>
      <template #title>
        <span>主页</span>
      </template>
    </el-menu-item>
    <el-menu-item index="status">
      <el-icon><icon-menu /></el-icon>
      <template #title>状态</template>
    </el-menu-item>
    <el-menu-item index="/counttime">
      <el-icon><Histogram /></el-icon>
      <template #title>统计</template>
    </el-menu-item>
    <el-menu-item index="/userview">
      <el-icon><UserFilled /></el-icon>
      <template #title>用户</template>
    </el-menu-item>
  </el-menu>
</template>

<script setup lang="ts">
import {ref} from 'vue'
import {
  Histogram,
  Menu as IconMenu,
  Location,
  UserFilled,
} from '@element-plus/icons-vue'

const isCollapse = ref(true)

</script>

<style>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 150px;
  min-height: 400px;
}
</style>
