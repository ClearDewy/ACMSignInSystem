<script setup lang="ts">
import Navigation from "./components/NavigationBar.vue"
import Head from "./components/Head.vue"

</script>

<template>
  <el-container style="width: 800px;height: 500px">
    <el-header style="-webkit-app-region: drag">
      <Head/>
    </el-header>

    <el-aside width="63px">
      <el-affix :offset="60">
        <Navigation style="position: absolute;z-index: 999"></Navigation>
      </el-affix>
    </el-aside>
    <el-main style="padding-left: 63px;">
      <router-view/>
    </el-main>
  </el-container>
</template>

<style scoped>

</style>
