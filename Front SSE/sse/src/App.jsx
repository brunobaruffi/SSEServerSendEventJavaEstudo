import { useEffect, useState } from "react";

export default function App() {
  const [messages, setMessages] = useState([]);
  useEffect(() => {
    // Cria uma nova conexão SSE com o endpoint /sse
    const eventSource = new EventSource("http://localhost:8080/sse");
    // Adiciona um listener para receber mensagens
    eventSource.onmessage = (event) => {
      //const message = JSON.parse(event.data);
      const message = event.data;
      setMessages((prevMessages) => [...prevMessages, message]);
    };
    // Fecha a conexão SSE quando o componente é desmontado
    return () => {
      eventSource.close();
    };
  }, []);
  return (
    <div>
      {" "}
      <h1>Server-Sent Events</h1>{" "}
      <ul>
        {" "}
        {messages.map((message, index) => (
          <li key={index}>{message}</li>
        ))}{" "}
      </ul>{" "}
    </div>
  );
}
