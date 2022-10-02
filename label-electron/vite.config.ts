import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import path from 'path'

export default defineConfig({
  plugins: [vue()],
  base: './',

  server: {
    open: true,
    // 配置代理
    proxy: {
      '/spring': {
        target: 'http://localhost:8080/api',
        ws: true,
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/spring/, ''),
      },
    },
  },

})
