FROM node:20.14.0-alpine
WORKDIR /frontend
COPY package*.json /frontend/
RUN npm install
ENV BACKEND_URL="http://localhost:8080"
EXPOSE 3000
COPY . .
CMD [ "npm", "start"]