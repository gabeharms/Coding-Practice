export EMCC_DEBUG=1

mandelbrot.js: mandelbrot.cpp
	em++ --bind --std=c++11 mandelbrot.cpp -s WASM=1 -o mandelbrot.js -s "EXTRA_EXPORTED_RUNTIME_METHODS=['addOnPostRun']"

clean:
	rm *.js *.wasm
