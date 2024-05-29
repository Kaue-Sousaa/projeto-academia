export default (function () {
  let locale = window.navigator.userLanguage || window.navigator.language;
  locale = locale === "en-US" ? "en-US" : "pt-BR";
  return locale;
})();
