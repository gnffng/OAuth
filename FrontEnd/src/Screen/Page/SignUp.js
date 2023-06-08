import axiosCsrf from "../../Module/interceptor/axiosCsrfInterceptor";

const getAuthentication = () => {
    axiosCsrf.get("/oauth2/authentication").then((resp) => {
        console.log(resp);
    })
}

const getUserData = () => {
    axiosCsrf.get("/oauth2/userData").then((resp) => {
        console.log(resp);
    })
}

const logout = () => {
    axiosCsrf.post("/oauth2/logout").then((resp) => {
        console.log(resp);
        window.location.assign("/");
    });
}

export default function SignUp() {

    return (
        <main style={{ padding: "1rem 0" }}>
            <h2>SignUp</h2>
            <button onClick={logout}>Logout</button>
            <button onClick={getUserData}>유저정보</button>
            <button onClick={getAuthentication}>인증정보</button>
        </main>
    );
}