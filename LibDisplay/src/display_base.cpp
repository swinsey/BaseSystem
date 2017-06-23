#include "display.h"

BSEXPORT bool bs::display::cmp_handles(const bs::display::handle_t& h1, const bs::display::handle_t& h2) {
	return h1.data1 == h2.data1 &&
		h1.data2 == h2.data2 &&
		h1.data3 == h2.data3 &&
		h1.data4 == h2.data4;
}