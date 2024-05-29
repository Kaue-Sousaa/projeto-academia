import React from "react";
import "./global.css";
import Routes from "./routes";

import locale from "./utils/helpers";

if (locale) {
  locale === "pt-BR"
    ? require("moment/locale/pt-br")
    : require("moment/locale/en-gb");
}

export default function App() {
  return <Routes />;
}
