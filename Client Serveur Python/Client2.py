import socket

# Connexion au serveur
server_address = ('localhost', 2002)
client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
client_socket.connect(server_address)

# Boucle principale
while True:
    # Lecture de l'entrée utilisateur
    request = input()
    # Envoi des données au serveur
    client_socket.send(request.encode())
    # Lecture de la réponse du serveur
    response = client_socket.recv(1024).decode().strip()
    # Affichage de la réponse si elle est non vide
    if response:
        print("Response =>", response)