# Blockchain Spring Boot Application

## Description

Ce projet est une application de blockchain simple développée avec Spring Boot. L'application permet de gérer une blockchain basique avec des fonctionnalités pour créer de nouvelles transactions, miner de nouveaux blocs et récupérer la chaîne complète de blocs.

## Fonctionnalités

- Créer de nouvelles transactions
- Miner de nouveaux blocs
- Récupérer la chaîne complète de blocs

## Prérequis

Avant de commencer, assurez-vous d'avoir les outils suivants installés sur votre machine :

- JDK (Java Development Kit) 11 ou supérieur
- Maven

## Installation

1. Clonez le repository :

    ```sh
    git clone https://github.com/votre-utilisateur/blockchain-springboot.git
    cd blockchain-springboot
    ```

2. Assurez-vous d'avoir les dépendances nécessaires dans votre fichier `pom.xml` :

    ```xml
    <dependencies>
        <!-- Spring Boot dependencies -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <!-- Lombok dependency -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.22</version>
            <scope>provided</scope>
        </dependency>

        <!-- Apache Commons Codec dependency -->
        <dependency>
            <groupId>commons-codec</groupId>
            <artifactId>commons-codec</artifactId>
            <version>1.15</version>
        </dependency>
    </dependencies>
    ```

## Utilisation

### Compilation et Exécution

#### Option 1 : Utiliser Maven pour compiler et exécuter

1. Ouvrez un terminal et naviguez vers le répertoire racine du projet.

    ```sh
    cd /path/to/your/project
    ```

2. Compilez et packagez l'application :

    ```sh
    mvn clean package
    ```

3. Exécutez l'application en utilisant le fichier JAR généré :

    ```sh
    java -jar target/blockchain-0.0.1-SNAPSHOT.jar
    ```

#### Option 2 : Utiliser le plugin Spring Boot Maven

1. Ouvrez un terminal et naviguez vers le répertoire racine du projet.

    ```sh
    cd /path/to/your/project
    ```

2. Lancez l'application directement :

    ```sh
    mvn spring-boot:run
    ```

### Tester l'API REST

Utilisez un outil comme Postman ou curl pour interagir avec l'API REST de l'application.

#### Créer une nouvelle transaction

```sh
curl -X POST -H "Content-Type: application/json" -d '{"sender": "Alice", "recipient": "Bob", "amount": 10}' http://localhost:8080/api/transactions/new
