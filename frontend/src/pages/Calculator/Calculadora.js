import { useState } from 'react';
import { calculateExpression } from '../../services/api';
import Display from '../../components/Display/Display';
import Keypad from '../../components/Keypad/Keypad';
import './Calculadora.css';

const Calculadora = () => {
    const [input, setInput] = useState('');
    const [result, setResult] = useState('0');
    const [isLoading, setIsLoading] = useState(false);

    const handleClick = async (value) => {
        if (value === '=') {
            if (!input) return;

            setIsLoading(true);
            try {
                const data = await calculateExpression(input);
                setResult(data.resultado.toString());
            } catch (error) {
                setResult('Erro: ' + error.message);
            } finally {
                setIsLoading(false);
            }
        } else if (value === 'C') {
            setInput('');
            setResult('0');
        } else {
            setInput(prev => prev + value);
        }
    };

    return (
        <div className="calculator-container">
            <Display value={input} />
            <div className="result">
                {isLoading ? 'Calculando...' : result}
            </div>
            <Keypad onClick={handleClick} disabled={isLoading} />
        </div>
    );
};

export default Calculadora;