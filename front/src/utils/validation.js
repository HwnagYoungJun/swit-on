function validateEmail(email) {
	const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	return re.test(String(email).toLowerCase());
}
function validatePassword(password) {
	const re = /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;
	return re.test(String(password).toLowerCase());
}
function validateName(name) {
	var special_pattern = /[~!@#$%^&*()_+|<>?:{}]/;
	var space = /\s/g;
	if (special_pattern.test(name) === false && space.test(name) === false) {
		return true;
	} else {
		return false;
	}
}
export { validateEmail, validatePassword, validateName };
