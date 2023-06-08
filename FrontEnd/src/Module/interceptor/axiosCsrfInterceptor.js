import axios from "axios";

const axiosCsrf = axios.create();

// 요청 인터셉터
axiosCsrf.interceptors.request.use(
    async function (config) {
        const csrfResp = await axios.get("/oauth2/csrf");
        config.headers = {
            'X-CSRF-TOKEN': csrfResp.data.token,
        };

        return config;
    },
    function (error) {
        return Promise.reject(error);
    },
);

// 응답 인터셉터
axiosCsrf.interceptors.response.use(
    function (response) {
        return response;
    },
    async function (error) {
        const originalConfig = error.config;

        console.log(error);

        if (error.response.status === 401 && !originalConfig._retry) {
            originalConfig._retry = true;

            return axiosCsrf(originalConfig);
        }

        return Promise.reject(error);
    },
);

export default axiosCsrf;
