<template>
  <el-affix>
    <el-menu
        class="el-menu-demo"
        mode="horizontal"
        :ellipsis="false"
    >
      <el-image :src="logo" style="height:60px;padding-left: 0"></el-image>
      <div class="flex-grow" />
        <el-button @click="minWindow" plain :icon="SemiSelect" style="-webkit-app-region:no-drag;border: none"></el-button>
        <el-button @click="closeWindow" plain :icon="CloseBold" style="-webkit-app-region:no-drag;margin: 0;border: none"></el-button>

    </el-menu>
  </el-affix>

</template>

<script lang="ts" setup>
import {SemiSelect,CloseBold} from '@element-plus/icons-vue'

import logo from '../assets/logo.png'

const closeWindow=()=>{
  window.myApi.closeWindow()
}
const minWindow=()=>{
  window.myApi.minWindow()
}



</script>

<style>
.flex-grow {
  flex-grow: 1;
}
</style>
