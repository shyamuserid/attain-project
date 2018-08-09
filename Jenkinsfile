def REPO_URL = "https://github.com/shyamuserid/attain-project.git"

node {
	
	deleteDir()
	
	stage("INIT") {
		GIT_BRANCH = GIT_BRANCH.replace("origin/", "")
		git(
            url: "${REPO_URL}",
            credentialsId: 'd09bb819-ed8e-427e-bb9d-e72b1b7092ac',
            branch: "${GIT_BRANCH}"
        )
        currentBuild.setDisplayName("${GIT_BRANCH}-${BUILD_NUMBER}")
	}
	
	stage("BUILD") {
	    sh """
	    	./gradlew clean build --debug --full-stacktrace
	    """
	}

}