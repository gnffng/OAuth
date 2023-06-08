import axiosCsrf from "../../Module/interceptor/axiosCsrfInterceptor";

// axios.defaults.xsrfCookieName = 'csrftoken';
// axios.defaults.xsrfHeaderName = 'X-CSRFTOKEN';

const oAuthGoogleLogin = () => {
    window.location.assign("/oauth2/authorization/google");
}

const oAuthKakaoLogin = () => {
    window.location.assign("/oauth2/authorization/kakao");
}

const testGet = () => {
    axiosCsrf.get("/test").then((resp) => {
        console.log(resp);
    })
}
export default function Home() {
    return (
        <main style={{ padding: "1rem 0" }}>
            <h2>Home</h2>
            <button onClick={oAuthGoogleLogin}>Google Login</button>
            <button onClick={oAuthKakaoLogin}>Kakao Login</button>
            <button onClick={testGet}>Test</button>
        </main>
    );
}