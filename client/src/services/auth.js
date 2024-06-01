import api from "./api";

const AUTH = process.env.REACT_APP_AUTH;

const cleanSession = () => {
  localStorage.removeItem(AUTH);
};

const auth = {
  getRoles() {
    const authData = localStorage.getItem(AUTH);
    if (authData) {
      const { role } = JSON.parse(authData);
      return [role];
    }
    return false;
  },

  isAuthenticated() {
    return localStorage.getItem(AUTH) !== null;
  },

  login(authData) {
    localStorage.setItem(AUTH, JSON.stringify(authData));
  },

  getRefreshToken() {
    const authData = localStorage.getItem(AUTH);
    const { refresh_token: refreshToken } = JSON.parse(authData);
    return refreshToken;
  },

  async logout() {
    await api
      .delete("/auth/logout")
      .then(() => {
        cleanSession();
      })
      .catch(() => {
        cleanSession();
      });
  },

  getUser() {
    const authData = localStorage.getItem(AUTH);
    return authData ? JSON.parse(authData) : null;
  },
};

export default auth;
