module.exports = {
  devServer: {
    port: 80, // 端口
  },

  lintOnSave: false ,// 取消 eslint 验证
  publicPath: "./",
  productionSourceMap: process.env.NODE_ENV === 'production' ? false : true
}
