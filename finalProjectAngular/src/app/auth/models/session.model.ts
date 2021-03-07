  
interface User {
  id: string
  username: string
  name: string
}

export interface Session {
  token: string
  user: User
}