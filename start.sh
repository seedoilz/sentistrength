#!/bin/bash

url="https://git.nju.edu.cn/api/v4/projects/8331/packages/generic/my_package/0.0.1/SentiStrength.jar"

function release()
{
  cd /var/lib/jenkins/workspace/test
  curl --header "PRIVATE-TOKEN: glpat-vHkkwBeo8wbELj6yW3sr" \
       --upload-file target/SentiStrength.jar \
       ${url}
  curl --header 'Content-Type: application/json' --header "PRIVATE-TOKEN: lpat-vHkkwBeo8wbELj6yW3sr" \
       --data '{ "name": "release", "tag_name": "v1.0.0", "description": "Super nice release", "milestones": ["v1.0"], "assets": { "links": [{ "name": "hoge", "url": "https://git.nju.edu.cn/api/v4/projects/8331/packages/generic/my_package/0.0.1/SentiStrength.jar", "direct_asset_path": "/binaries/linux-amd64", "link_type":"other" }] } }' \
       --request POST "https://git.nju.edu.cn/api/v4/projects/8331/releases"

}

release
