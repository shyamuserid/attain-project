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
	    	./gradlew clean build
	    """
	}

	stage("PUBLISH JAR") {
		sh """
			aws s3 cp demo-java-0.0.1-SNAPSHOT.jar s3://attain-project-repo
		"""
	}

}