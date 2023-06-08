const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = (app) => {
    app.use(
        '/oauth2',
        createProxyMiddleware({
            target: 'http://localhost:15000',
            changeOrigin: true,
        })
    );

    app.use(
        '/test',
        createProxyMiddleware({
            target: 'http://localhost:15000',
            changeOrigin: true,
        })
    );
};

