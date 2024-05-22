const roles = {
  admin: ["ADMIN"],
  user: ["USER"],
};

const rolesPath = [
  {
    path: "/treino-aluno",
    role: roles.admin,
  },
  {
    path: "/home",
    role: roles.user,
  },
  /* 
  {
    path: "/register",
    role: [roles.admin[0], roles.user[0]],
  }, */
];

export default rolesPath;
