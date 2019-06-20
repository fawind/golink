all: install

install:
	(cd app && npm install) && ./service/gradlew -p ./service build

test:
	./service/gradlew -p ./service check

runService:
	./service/gradlew -p ./service appengineRun

runApp:
	(cd app && npm start)

build: test
	(cd app && npm run dist) && ./service/gradlew -p ./service build

deploy: build
	./service/gradlew -p ./service appengineDeploy

