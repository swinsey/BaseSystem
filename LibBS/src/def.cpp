#include "bs_def.h"

BSEXPORT bool bs::is_success(result_t r) {
	return r >= 0;
}
BSEXPORT bool bs::is_error(result_t r) {
	return r < 0;
}

BSEXPORT bs::scope_t bs::get_scope(result_t result) {
	if (is_error(result)) {
		result *= -1;
	}

	return result >> 24;
}
BSEXPORT bs::library_t bs::get_library(result_t result) {
	if (is_error(result)) {
		result *= -1;
	}

	return (result >> 16) & 0xFF;
}
BSEXPORT bs::subset_t bs::get_subset(result_t result) {
	if (is_error(result)) {
		result *= -1;
	}

	return (result >> 8) & 0xFF;
}
BSEXPORT bs::code_t bs::get_code(result_t result) {
	if (is_error(result)) {
		result *= -1;
	}

	return result & 0xFF;
}