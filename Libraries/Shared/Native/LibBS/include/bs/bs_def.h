#ifndef _SHARED_BS_DEV_H_
#define _SHARED_BS_DEV_H_

#if defined(WIN32)
#define BSEXPORT __declspec(dllexport)
#else
#define BSEXPORT
#endif

#include <boost/assert.hpp>
#include <boost/stacktrace.hpp>
#include <stdint.h>
#include <string>
#include <string.h>
#include <iostream>
#include "bs_util.h"

namespace bs {
	//////
	///Assert
	//////
#undef assert
	void assert(bool expr);
	void assert_msg(bool expr, std::string msg);
	void assert_msg(bool expr, const char* msg);
	void assert_always(bool expr);
	void assert_msg_always(bool expr, std::string msg);
	void assert_msg_always(bool expr, const char* msg);

	inline void assert(bool expr) {
#ifndef NDEBUG
		assert_always(expr);
#endif
	}
	inline void assert_msg(bool expr, std::string msg) {
#ifndef NDEBUG
		assert_msg_always(expr, msg);
#endif
	}
	inline void assert_msg(bool expr, const char* msg) {
#ifndef NDEBUG
		assert_msg_always(expr, msg);
#endif
	}
	inline void assert_always(bool expr) {
		assert_msg_always(expr, nullptr);
	}
	inline void assert_msg_always(bool expr, std::string msg) {
		assert_msg_always(expr, msg.c_str());
	}
	inline void assert_msg_always(bool expr, const char* msg) {
		if (expr) return;

		std::cerr << "Assertion Failed";
		if (msg != nullptr) std::cerr << ": " << msg;
		std::cerr << std::endl;
		std::cerr << boost::stacktrace::stacktrace() << std::endl;
	}

	//////
	///Types and Sizes
	//////

	/*
		Currently code_t is 8 bit, so result_t can be 32bit (as most existing java code including systemcalls assume 32bit result codes)
		It would appropriate to expand code_t to 32 bit, so return codes of external libraries can be embedded
	*/

	typedef int32_t result_t;
	typedef uint8_t scope_t;
	typedef uint8_t library_t;
	typedef uint8_t subset_t;
	typedef uint8_t code_t;

	const size_t BITS_OF_RESULT_T = util::size_in_bits<result_t>::value;
	const size_t BITS_OF_SCOPE_T = util::size_in_bits<scope_t>::value;
	const size_t BITS_OF_LIBRARY_T = util::size_in_bits<library_t>::value;
	const size_t BITS_OF_SUBSET_T = util::size_in_bits<subset_t>::value;
	const size_t BITS_OF_CODE_T = util::size_in_bits<code_t>::value;

	const result_t RESULT_PADDING = BITS_OF_RESULT_T - BITS_OF_SCOPE_T - BITS_OF_LIBRARY_T - BITS_OF_SUBSET_T - BITS_OF_CODE_T;
	const result_t OFFSET_OF_SCOPE = util::size_in_bits<result_t>::value - RESULT_PADDING - util::size_in_bits<scope_t>::value;
	const result_t OFFSET_OF_LIBRARY = OFFSET_OF_SCOPE - util::size_in_bits<library_t>::value;
	const result_t OFFSET_OF_SUBSET = OFFSET_OF_LIBRARY - util::size_in_bits<subset_t>::value;
	const result_t OFFSET_OF_CODE = OFFSET_OF_SUBSET - util::size_in_bits<code_t>::value;

	/*
		If true, the first bit of scope_t is reserved an cannot be used for scope values
	*/
	const bool SHRINKED_SCOPE = RESULT_PADDING == 0;

	static_assert(OFFSET_OF_SCOPE >= 0, "Invalid Offset. Size of result_t seems to be too small to store all required data");
	static_assert(OFFSET_OF_LIBRARY >= 0, "Invalid Offset. Size of result_t seems to be too small to store all required data");
	static_assert(OFFSET_OF_SUBSET >= 0, "Invalid Offset. Size of result_t seems to be too small to store all required data");
	static_assert(OFFSET_OF_CODE >= 0, "Invalid Offset. Size of result_t seems to be too small to store all required data");

	const result_t BITMASK_SCOPE = util::bitmask<BITS_OF_SCOPE_T, decltype(BITMASK_SCOPE)>::value;
	const result_t BITMASK_LIBRARY = util::bitmask<BITS_OF_LIBRARY_T, decltype(BITMASK_LIBRARY)>::value;
	const result_t BITMASK_SUBSET = util::bitmask<BITS_OF_SUBSET_T, decltype(BITMASK_SUBSET)>::value;
	const result_t BITMASK_CODE = util::bitmask<BITS_OF_CODE_T, decltype(BITMASK_CODE)>::value;

	//////
	///Constant Expressions
	//////
	constexpr BSEXPORT bool is_success(result_t r) {
		return r >= 0;
	}
	constexpr BSEXPORT bool is_error(result_t r) {
		return r < 0;
	}

	constexpr BSEXPORT result_t create_result(bool success, scope_t scope, library_t library, subset_t subset, code_t code) {

		result_t t = 0;
		t |= static_cast<result_t>(scope & 0xFF) << OFFSET_OF_SCOPE;
		t |= static_cast<result_t>(library & 0xFF) << OFFSET_OF_LIBRARY;
		t |= static_cast<result_t>(subset & 0xFF) << OFFSET_OF_SUBSET;
		t |= static_cast<result_t>(code & 0xFF) << OFFSET_OF_CODE;

		if (!success) {
			t *= -1;
		}

		return t;
	}
	constexpr BSEXPORT scope_t get_scope(result_t result) {
		return (result >> OFFSET_OF_SCOPE) & BITMASK_SCOPE;
	}
	constexpr BSEXPORT library_t get_library(result_t result) {
		return (result >> OFFSET_OF_LIBRARY) & BITMASK_LIBRARY;
	}
	constexpr BSEXPORT subset_t get_subset(result_t result) {
		return (result >> OFFSET_OF_SUBSET) & BITMASK_SUBSET;
	}
	constexpr BSEXPORT code_t get_code(result_t result) {
		return (result >> OFFSET_OF_CODE) & BITMASK_CODE;
	}

	//////
	///Scopes
	//////
	const scope_t SCOPE_USER = 0x01;
	const scope_t SCOPE_SYSTEM = 0x02;
	const scope_t SCOPE_MIXED = SCOPE_USER | SCOPE_SYSTEM;

	//////
	///BS Library
	//////
	const library_t LIBRARY_BS = 1;
	const subset_t SUBSET_BS_JNI = 1;
	
	//////
	///SDLNative Library
	//////
	const library_t LIBRARY_SDLNATIVE = 2;
	const subset_t SUBSET_SDLNATIVE = 1;

    //////
    ///DisplayManager Library
    //////
    const library_t LIBRARY_DSPMNGR = 3;
    const subset_t SUBSET_DSPMNGR = 1;
}

#endif