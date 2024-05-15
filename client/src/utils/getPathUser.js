import rolesPath from "./roles";

export default function getPathUser(role) {
  const array = [];
  rolesPath?.forEach((elem) => {
    role?.forEach((role) => {
      if (elem?.role?.includes(role)) {
        array.push(elem?.path);
      }
    });
  });
  return array;
}
