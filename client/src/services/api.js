import axios from "axios";

const api = axios.create({
  baseURL: process.env.REACT_APP_AUTH_BASE_URL,
  headers: {
    "Content-Type": "application/json",
  },
});

api.interceptors.request.use(async (config) => {
  const auth = localStorage.getItem("ACAD");
  if (auth) {
    const { accessToken } = JSON.parse(auth);
    const tmpConfig = config;
    if (accessToken) {
      tmpConfig.headers.Authorization = accessToken;
    }
  }
  return config;
});

export default api;
