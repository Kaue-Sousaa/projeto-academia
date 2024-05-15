const roles = {
  admin: ["ADMIN"],
  user: ["USER"],
};

const rolesPath = [
  {
    path: "/home",
    role: roles.admin,
  },
  /* 
  {
    path: "/register",
    role: [roles.admin[0], roles.user[0]],
  }, */
];

export default rolesPath;
