Запуск приложения


helm install myapp ./k8s/hello-charts --atomic

Запуск auth


helm install auth ./k8s/auth --atomic

Установка ingress


kubectl apply -f ./k8s/app-ingress.yaml
kubectl apply -f ./k8s/auth-ingress.yaml

Проверка postman

newman run auth.postman_collection.json
