# documentation BlockchainController

## EndPoints

| methode | endpoints | explications | paramètres | 
|---------|-----------|--------------|------------|
|    POST      |    /api/transactions/new       |    Crée une nouvelle transaction et l'ajoute à la liste des transactions en attente. Retourne l'index du bloc dans lequel la transaction sera ajoutée.           | Transaction (envoyé dans le corps de la requête)           |
|    GET     |     /api/mine      |       Mines (crée) un nouveau bloc en trouvant une preuve de travail valide, ajoute une transaction de récompense et ajoute le bloc à la blockchain. Retourne le nouveau bloc miné.        | Aucun           |
|   GET      |    /api/chain       |   Retourne la chaîne complète de blocs et sa longueur.           |   Aucun           |