# Application Blockchain avec Spring Boot

Cette application est une implémentation simple d'une blockchain utilisant Spring Boot. Elle inclut des fonctionnalités pour gérer les transactions, miner des blocs et visualiser la blockchain.

## Table des Matières
- [Prérequis](#prérequis)
- [Installation](#installation)
- [Lancement de l'Application](#lancement-de-lapplication)
- [Endpoints API](#endpoints-api)
- [Tester avec Postman](#tester-avec-postman)
- [Structure du Projet](#structure-du-projet)

## Prérequis
- Java 17 ou supérieur
- Maven 3.6.0 ou supérieur
- Base de données MySQL

## Installation

1. **Cloner le dépôt :**
   ```bash
   git clone https://github.com/Archange75019/blockchain-springboot.git
   cd blockchain-springboot

## Lancement de l'Application
Pour démarrer l'application, utilisez la commande suivante :

```bash
mvn spring-boot:run
```
L'application sera accessible à l'adresse suivante : http://localhost:8080

## Tester avec Postman
### Créer une transaction :

URL : http://localhost:8080/api/transactions
Méthode : POST
Body :
```bash
{
"sender": "10",
"recipient": "Bob",
"amount": 5678
}
```
### Miner un bloc :
Voir la blockchain
URL : http://localhost:8080/api/mine?minerAddress=miner1
Méthode : POST

URL : http://localhost:8080/api/chain
Méthode : GET