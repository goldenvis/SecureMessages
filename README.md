# SecureMessages

[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

## Description

This project is a simple binary message encoding scheme designed and implemented for use in a signaling protocol. The encoding scheme is intended to facilitate the exchange of messages between peers in a real-time communication application.

## Message Model

The message model is defined as follows:

* A message can contain a variable number of headers and a binary payload.
* Headers consist of name-value pairs, where both the names and values are ASCII-encoded strings.
* Header names and values are limited to 1023 bytes each, independently.
* A message can have a maximum of 63 headers.
* The message payload is limited to 256 KiB.
  
## Contributing

Welcome contributors to your project. Mention guidelines for contributing, such as the process for submitting pull requests or reporting issues. Also, include information on how contributors will be recognized (e.g., adding their names to a CONTRIBUTORS file).

## License
This project is licensed under the MIT License.

## Contact
For any questions, feedback, or suggestions regarding this binary message encoding scheme, please feel free to contact us at chmt.raghuveer@gmail.com. We welcome any contributions or improvements to enhance the efficiency and robustness of the encoding scheme for real-time communication applications.
