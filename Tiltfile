# Build
custom_build(
    # Name of container image
    ref='catalog-service',
    # Command to build container image
    command='./gradlew bootBuildImage --imageName $EXPECTED_REF',
    # Files to watch that trigger a new build
    deps=['build.gradle', 'src']
)

# Deploy
k8s_yaml(['k8s/catalog-service.yml'])

# Manage
k8s_resource('catalog-service', port_forwards=['9001'])