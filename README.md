<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chữ VIN với hiệu ứng CSS</title>
    <style>
        body {
            margin: 0;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            background: linear-gradient(135deg, #1a1a2e, #16213e, #0f3460);
            overflow: hidden;
            font-family: 'Arial', sans-serif;
        }
        
        .container {
            position: relative;
        }
        
        .vin-text {
            font-size: 8rem;
            font-weight: bold;
            letter-spacing: 0.5rem;
            text-transform: uppercase;
            color: transparent;
            background: linear-gradient(
                145deg,
                #f8f9fa 0%,
                #e9ecef 20%, 
                #adb5bd 40%,
                #ced4da 60%,
                #dee2e6 80%,
                #f8f9fa 100%
            );
            background-size: 200% auto;
            background-clip: text;
            -webkit-background-clip: text;
            text-shadow: 0 10px 20px rgba(0, 0, 0, 0.2);
            animation: shine 3s linear infinite, float 4s ease-in-out infinite;
            position: relative;
        }
        
        .vin-text::before {
            content: "VIN";
            position: absolute;
            top: 0;
            left: 0;
            color: transparent;
            background: linear-gradient(
                to right,
                transparent 0%,
                rgba(255, 255, 255, 0.5) 50%,
                transparent 100%
            );
            background-clip: text;
            -webkit-background-clip: text;
            animation: sparkle 5s linear infinite;
            background-size: 200% 100%;
        }
        
        .glow {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            width: 0;
            height: 0;
            border-radius: 50%;
            background: radial-gradient(circle, rgba(255,255,255,0.8) 0%, rgba(255,255,255,0) 70%);
            opacity: 0;
            animation: glow 4s ease-in-out infinite;
        }
        
        .particles {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
        }
        
        .particle {
            position: absolute;
            width: 5px;
            height: 5px;
            background: white;
            border-radius: 50%;
            opacity: 0;
            animation: particle 3s ease-in-out infinite;
        }
        
        @keyframes shine {
            0% {
                background-position: 200% center;
            }
            100% {
                background-position: -200% center;
            }
        }
        
        @keyframes sparkle {
            0% {
                background-position: -200% 0;
            }
            100% {
                background-position: 200% 0;
            }
        }
        
        @keyframes float {
            0%, 100% {
                transform: translateY(0);
            }
            50% {
                transform: translateY(-20px);
            }
        }
        
        @keyframes glow {
            0%, 100% {
                width: 0;
                height: 0;
                opacity: 0;
            }
            50% {
                width: 400px;
                height: 400px;
                opacity: 0.3;
            }
        }
        
        @keyframes particle {
            0% {
                transform: translate(0, 0);
                opacity: 0;
            }
            25% {
                opacity: 1;
            }
            75% {
                opacity: 1;
            }
            100% {
                transform: translate(var(--x), var(--y));
                opacity: 0;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="vin-text">VIN</div>
        <div class="glow"></div>
        <div class="particles" id="particles"></div>
    </div>

    <script>
        // Tạo hiệu ứng hạt
        const particlesContainer = document.getElementById('particles');
        const numParticles = 30;
        
        function createParticles() {
            for (let i = 0; i < numParticles; i++) {
                const particle = document.createElement('div');
                particle.classList.add('particle');
                
                // Vị trí bắt đầu ngẫu nhiên
                const startX = Math.random() * 300 - 150;
                const startY = Math.random() * 100 - 50;
                
                // Vị trí kết thúc ngẫu nhiên
                const x = (Math.random() - 0.5) * 400;
                const y = (Math.random() - 0.5) * 400;
                
                // Delay ngẫu nhiên
                const delay = Math.random() * 3;
                
                particle.style.setProperty('--x', `${x}px`);
                particle.style.setProperty('--y', `${y}px`);
                particle.style.left = `${startX}px`;
                particle.style.top = `${startY}px`;
                particle.style.animationDelay = `${delay}s`;
                
                particlesContainer.appendChild(particle);
            }
        }
        
        createParticles();
    </script>
</body>
</html>
