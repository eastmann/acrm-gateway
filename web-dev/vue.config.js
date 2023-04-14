module.exports = {
    devServer: {
        port: 3000,
        proxy: 'http://localhost:38150'
    },
    outputDir: "../src/main/resources/static",
    // transpileDependencies: [
    //   'vuetify'
    // ]
};