import socket
import threading

LISTEN = True

def listen_to_response(sock):
    global LISTEN
    while LISTEN:
        data = sock.recv(1024).decode() # receive response
        print('Received from server: ' + data) # show in terminal

def client_program():
    host = "localhost" # Host name
    port = 2001 # socket server port number

    with socket.socket() as client_socket: # instantiate and auto-close
        client_socket.connect((host, port)) # connect to the server
        thread = threading.Thread(target=listen_to_response, args=(client_socket,))
        thread.start()

        request = ""
        while request.lower().strip() != 'bye':
            request = input(" -> ")
            client_socket.send(request.encode()) # send message

        LISTEN = False # signal the listening thread to stop
        thread.join() # wait for the thread to finish

if __name__ == '__main__':
    client_program()
