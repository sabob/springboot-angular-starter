export default {
  isEmpty(str) {

    if ( str == null ) return true;

    if (typeof str === 'string') {
      return (0 === str.trim().length);
    }
    return false;
  },

  isNotEmpty(str) {
    return !this.isEmpty(str);
  },

  trim(str) {
    if ( typeof str === 'string')
    if (this.isNotEmpty(str)) {
      return str.trim();
    }
    return str;
  }
}
