import socket

def main():
    server_address = "localhost"
    port = 2001
    with socket.socket() as client_socket:
        client_socket.connect((server_address, port))
        input_stream = client_socket.makefile('r', encoding='utf-8')
        output_stream = client_socket.makefile('w', encoding='utf-8', newline='\r\n')
        console_input = input
        print("Connected!")
        while True:
            message = console_input(" -> ")
            output_stream.write(message + '\r\n')
            output_stream.flush()
            if message == "exit":
                break
            response = input_stream.readline().rstrip('\r\n')
            print("Received from server:", response)
        client_socket.close()

if __name__ == '__main__':
    main()
