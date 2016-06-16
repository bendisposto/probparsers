# this is just a simple reminder on what kind of gradle tasks should be run
# deploy is the target to build the parsers and doesn't seem to do any deployment
# there does not seem to be a way to just build the probcliparser.jar

deploy:
	echo "Building ProB parsers"
	gradle deploy
uberjar:
	echo "Building Uberjar"
	gradle clean uberjar
clean:
	echo "Cleaning; useful if you encounter weird syntax errors during building"
	gradle clean
