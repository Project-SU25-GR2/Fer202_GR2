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
