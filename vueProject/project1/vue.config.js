const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    port: 7070,//端口号
    proxy: "http://localhost:8080",//代理
  },
  
})