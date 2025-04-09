import axios from 'axios';

const API_URL = 'http://localhost:8071/api/calculadora';

export const calculateExpression = async (expression) => {
    try {
        const response = await axios.post(`${API_URL}/calcular`, {
            expressao: expression
        });
        return response.data;
    } catch (error) {
        throw new Error(error.response?.data?.message || 'Erro ao calcular');
    }
};