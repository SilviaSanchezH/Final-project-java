  
export class LogInResponseDto {
  data: {
    token: string
    user: {
      id: string
      username: string
      name: string
    }
  }
}