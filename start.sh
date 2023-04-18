#!/bin/bash
verson="1.0.2"
url="https://git.nju.edu.cn/api/v4/projects/8331/packages/generic/my_package/"+$verson+"/SentiStrength.jar"
dataInfo='{ "name": "release", "tag_name": "v1.0.2", "description": "2.0", "assets": { "links": [{ "name": "hoge", "url": "https://git.nju.edu.cn/api/v4/projects/8331/packages/generic/my_package/'+$verson + '/SentiStrength.jar", "direct_asset_path": "/binaries/arm64", "link_type":"other" }] } }'
function release()
{
  cd /var/lib/jenkins/workspace/test
  curl --header "PRIVATE-TOKEN: glpat-vHkkwBeo8wbELj6yW3sr" \
       --upload-file target/SentiStrength.jar \
       ${url}
  curl --header 'Content-Type: application/json' --header "PRIVATE-TOKEN: glpat-vHkkwBeo8wbELj6yW3sr" \
       --data $dataInfo \
       --request POST "https://git.nju.edu.cn/api/v4/projects/8331/releases"

}

release
