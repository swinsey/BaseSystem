#ifndef _SHARED_BS_UTIL_H_
#define _SHARED_BS_UTIL_H_

#include <boost\type_traits.hpp>

namespace bs {
	namespace util {
		template<typename T>
		T clamp(T val, T low, T high) {
			static_assert(boost::has_less<T>::value, "T less operator required");
			static_assert(boost::has_greater<T>::value, "T greater operator required");

			if (val < low) {
				return low;
			}

			if (val > high) {
				return high;
			}

			return val;
		}

		template<typename T>
		T lerp(T min, T max, float t) {
			static_assert(boost::has_multiplies<T, float>::value, "T multiply(T * float) operator required");

			t = clamp(t, tMin, tMax);

			return min + (T)((max - min) * t);
		}

		template<typename T, typename U>
		T translate(T tMin, T tMax, U uMin, U uMax, U val) {
			static_assert(boost::has_minus<T>::value, "T minus operator required");
			static_assert(boost::has_plus<T>::value, "T plus operator required");
			static_assert(boost::is_convertible<float, T>::value, "Conversion float -> T required");

			static_assert(boost::has_minus<U>::value, "U minus operator required");
			static_assert(boost::has_plus<U>::value, "U plus operator required");
			static_assert(boost::is_convertible<U, float>::value, "Conversion U -> float required");

			T dT = tMax - tMin;
			U dU = uMax - uMin;

			float progress = (float)(val - dU) / (float)(uMax - dU);

			return (T)(dT * progress) + tMin;
		}
	}
}

#endif