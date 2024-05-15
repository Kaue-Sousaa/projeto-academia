import PropTypes from "prop-types";
import { Route, Navigate, useLocation} from "react-router-dom";

import auth from "../services/auth";
import getPathUser from "../utils/getPathUser";
import { Children } from "react";

export default function RouteWrapper({
  children
}) {

  const {pathname} = useLocation();
  const signed = auth.isAuthenticated();
  const allowedRoutes = signed ? getPathUser(auth.getRoles()) : null;
  if (signed && !allowedRoutes?.includes(pathname)) {
    return <Navigate to={`${allowedRoutes[0]}`} />;
  }

  if (!signed ) {
    return <Navigate to="/" />;
  }

  if (signed && allowedRoutes.includes(pathname)) {
    return children;
  }
}

RouteWrapper.propTypes = {
  children: PropTypes.oneOfType([PropTypes.func, PropTypes.object]).isRequired
};

