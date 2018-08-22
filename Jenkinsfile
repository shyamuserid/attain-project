def REPO_URL = "https://github.com/shyamuserid/attain-project.git"

node {
	
	deleteDir()
	
	stage("INIT") {
		GIT_BRANCH = GIT_BRANCH.replace("origin/", "")
		git(
            url: "${REPO_URL}",
            credentialsId: 'jim-git-credentials',
            branch: "${GIT_BRANCH}"
        )
        currentBuild.setDisplayName("${GIT_BRANCH}-${BUILD_NUMBER}")
	}
	
	stage("BUILD") {
	    sh """
	    	./gradlew clean build -PjarVersion=0.0.${BUILD_NUMBER}-SNAPSHOT
	    """
	}

	stage("PUBLISH JAR") {
		sh """
			aws s3 cp demo-java/build/libs/demo-java-0.0.${BUILD_NUMBER}-SNAPSHOT.jar s3://attain-project-repo
		"""
	}

	stage("BUILD AND PUBLISH DOCKER") {
		sh """
			echo "Docker Login"
			set +x
			$(aws ecr get-login --no-include-email --region us-east-1)
			set -x
			docker build -t 927342992241.dkr.ecr.us-east-1.amazonaws.com/attain-project:${BUILD_NUMBER} -f demo-java/Dockerfile --build-arg JAR_FILE=build/libs/demo-java-0.0.${BUILD_NUMBER}-SNAPSHOT.jar demo-java/
			docker push 927342992241.dkr.ecr.us-east-1.amazonaws.com/attain-project:${BUILD_NUMBER}
			if [ "${GIT_BRANCH}" = "integrate" ]; then
				docker tag 927342992241.dkr.ecr.us-east-1.amazonaws.com/attain-project:${BUILD_NUMBER} 927342992241.dkr.ecr.us-east-1.amazonaws.com/attain-project:latest
				docker push 927342992241.dkr.ecr.us-east-1.amazonaws.com/attain-project:latest
			fi
		"""
	}

	stage("MERGE") {
		withCredentials([
			usernamePassword(credentialsId: "jim-git-credentials",
							passwordVariable: 'GIT_PASSWORD',
							usernameVariable: 'GIT_USERNAME')
		]) {
			def tag = "attain-project-${GIT_BRANCH}-${BUILD_NUMBER}"
			def origin = "https://${GIT_USERNAME}:${GIT_PASSWORD}@github.com/shyamuserid/attain-project.git"
			sh """
				git remote set-url origin https://github.com/shyamuserid/attain-project.git
				git config user.name "Jenkins"
				git config user.email "jenkins@blah.blorp"
				git tag -a ${tag} -m "Tagging ${tag}"
				git push ${origin} ${tag}

				if [[ ${GIT_BRANCH} =~ ^ci-.*\$ ]]; then
					git checkout -- .
					git checkout integrate
					git merge ${GIT_BRANCH}
					git push ${origin} integrate
				elif [ "${GIT_BRANCH}" = "integrate" ]; then
					git checkout -- .
					git checkout master
					git merge ${GIT_BRANCH}
					git push ${origin} master
				else
					exit 0
				fi
			"""
		}
	}

}