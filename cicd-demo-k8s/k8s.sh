echo "=======docker images copying utility========="
echo "listing directories..."
search=acoustic-search
users=acoustic-users
gateway=acoustic-gateway
registry=acoustic-registry
cmd="ls -l"
eval  $cmd
echo current directory [$(pwd)]

echo "***********************************"

echo "copying $gateway image to k8s repo...."
echo "docker save niksdocker2233/$gateway > $gateway.tar"
eval docker save "niksdocker2233/$gateway > $gateway/$gateway.tar"
echo "importing $gateway image to k8s repo...."
eval microk8s ctr image import "$gateway/$gateway.tar"
echo "microk8s ctr image import $gateway/$gateway.tar"
echo "============================...import $gateway done"

echo "copying $users image to k8s repo...."
echo "docker save niksdocker2233/$users > $users.tar"
#docker save niksdocker2233/acoustic-users > acoustic-users.tar
eval docker save "niksdocker2233/$users > $users/$users.tar"
echo "importing $users image to k8s repo...."
eval microk8s ctr image import "$users/$users.tar"
#microk8s ctr image import acoustic-users.tar
echo "microk8s ctr image import $users/$users.tar"
echo "============================...import $users done"

echo "copying $search image to k8s repo...."
echo "docker save niksdocker2233/$search > $search.tar"
eval docker save "niksdocker2233/$search > $search/$search.tar"
echo "importing $search image to k8s repo...."
eval microk8s ctr image import "$search/$search.tar"
echo "microk8s ctr image import $search/$search.tar"
echo "============================...import $search done"

echo "copying $registry image to k8s repo...."
echo "docker save niksdocker2233/$registry > $registry.tar"
eval docker save "niksdocker2233/$registry > $registry/$registry.tar"
echo "importing $registry image to k8s repo...."
eval microk8s ctr image import "$registry/$registry.tar"
echo "microk8s ctr image import $registry/$registry.tar"
echo "============================...import $registry done"